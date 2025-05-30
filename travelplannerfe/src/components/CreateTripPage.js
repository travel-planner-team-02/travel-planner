import React, { useState } from "react";
import { DatePicker, Button, Input, List, message } from "antd";
import dayjs from "dayjs"
import CreateTripDetail from "./CreateTripDetail";
import { getCities} from "../utils";

const { RangePicker } = DatePicker;

const CreateTripPage = ({ onCancel, onCityClick, isCreatingTrip }) => {
    const [step, setStep] = useState(1);
    const [tripDates, setTripDates] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [searchResults, setSearchResults] = useState([]);
    const [selectedCity, setSelectedCity] = useState(null);//is not same as another state, just for here


    // STEP 1: Handle trip date selection
    const handleDateChange = (dates) => {
        setTripDates(dates);
    };

    const goToNextStep = () => {
        if (step === 1) {
            if (!tripDates || tripDates.length !== 2) {
                message.error("Please select a start and end date.");
                return;
            }
            setStep(2);
        } else if (step === 2) {
            if (!selectedCity) {
                message.error("Please select a city.");
                return;
            }
            setStep(3);
        }
    };

    // STEP 2: Search cities
    const handleSearch = async () => {
        try {
            const allCities = await getCities();
            const filtered = allCities.filter((city) =>
                city.name.toLowerCase().includes(searchTerm.toLowerCase())
            );
            setSearchResults(filtered);
        } catch (error) {
            message.error("Failed to search cities.");
        }
    };

    const goToPreviousStep = () => {
        if (step === 2) {
            setStep(1);
        } else if (step === 3) {
            setStep(2);
        }
    };

    return (
        <div style={{ padding: 24, background: "#ecf0f1", minHeight: "100vh" }}>
            <h1>Create a New Trip</h1>

            {step === 1 && (
                <div style={{ marginTop: 32 }}>
                    <h2>Select Trip Dates</h2>
                    <RangePicker
                        onChange={handleDateChange}
                        format="YYYY-MM-DD"
                        style={{ marginBottom: 24 }}
                    />
                    <br />
                    <div style={{ display: "flex", gap: 8 }}>
                        <Button onClick={onCancel}>
                            Cancel
                        </Button>
                        <Button type="primary" onClick={goToNextStep}>
                            Next
                        </Button>
                    </div>
                </div>
            )}

            {step === 2 && (
                <div style={{ marginTop: 32 }}>
                    <h2>Search for Cities</h2>
                    <Input.Search
                        placeholder="Search city"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        onSearch={handleSearch}
                        enterButton
                        style={{ width: 300, marginBottom: 16 }}
                    />
                    <List
                        bordered
                        dataSource={searchResults}
                        renderItem={(item) => (
                            <List.Item
                                style={{ cursor: "pointer" }}
                                onClick={() => { 
                                    setSearchTerm(item.name);
                                    onCityClick(item);
                                    setSelectedCity(item);
                                }} // click on item to select
                            >
                            <div
                                style={{
                                display: "flex",
                                justifyContent: "space-between",
                                width: "100%",
                                }}
                            >
                            <span>{item.name}</span>
                                </div>
                        </List.Item>
                    )}
                    />
                    <div style={{ display: "flex", gap: 8, marginTop: 24 }}>
                        <Button onClick={goToPreviousStep}>Back</Button>
                        <Button onClick={goToNextStep}>Next</Button>
                        <Button danger onClick={onCancel}>Cancel</Button>
                    </div>
                </div>
            )}

            {step === 3 && (
                <CreateTripDetail
                    step={step}
                    city={selectedCity}
                    tripDates={tripDates}
                    onCancel={onCancel}
                    goToPreviousStep={goToPreviousStep}
                />
            )}
        </div>
    );
};

export default CreateTripPage;