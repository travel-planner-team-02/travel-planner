import React, { useState } from "react";
import { DatePicker, Button, Input, List, message } from "antd";
import dayjs from "dayjs";
import { getCities } from "../utils";

const { RangePicker } = DatePicker;

const CreateTripPage = ({ onCancel, onCityClick }) => {
    const [step, setStep] = useState(1);
    const [tripDates, setTripDates] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [searchResults, setSearchResults] = useState([]);

    // STEP 1: Handle trip date selection
    const handleDateChange = (dates) => {
        setTripDates(dates);
    };

    const goToNextStep = () => {
        if (!tripDates || tripDates.length !== 2) {
            message.error("Please select a start and end date.");
            return;
        }
        setStep(2);
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
        setStep(1);
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
                            <List.Item>
                                <div style={{ display: "flex", justifyContent: "space-between", width: "100%" }}>
                                    <span>{item.name}</span>
                                    <Button type="link" onClick={() => {
                                        console.log("Clicked on city:", item);
                                        onCityClick(item)
                                    }}>
                                        View Sites
                                    </Button>
                                </div>
                            </List.Item>
                        )}
                    />
                    <div style={{ display: "flex", gap: 8, marginTop: 24 }}>
                        <Button onClick={goToPreviousStep}>Back</Button>
                        <Button danger onClick={onCancel}>Cancel</Button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default CreateTripPage;