import React from "react";
import { Button } from "antd";
import TripList from "./TripList";
import TripDetail from "./TripDetail";
import CityDetail from "./CityDetail";
import CreateTripPage from "./CreateTripPage";

class TripSider extends React.Component {
    render() {
        const {
            username,
            isCreatingTrip,
            selectTrip,
            selectCity,
            trips,
            cities,
            tripsites,
            selectedCitySites,
            onCreateTripClick,
            onCancelCreateTrip,
            onTripClick,
            onSiteClick,
            onBack,
            onCityClick,
            onCityBack,
        } = this.props;

        return (
            <div style={{ width: 500, background: "#2c3e50", color: "#fff", height: "100%", padding: 16 }}>
                <div style={{ fontWeight: 600, fontSize: 18, marginBottom: 16 }}>
                    Hi {username}!
                </div>

                {isCreatingTrip ? (
                    <CreateTripPage
                        onCancel={onCancelCreateTrip}
                        onCityClick={onCityClick} />
                ) : (
                    <>
                        <Button type="primary" shape="round" block style={{ marginBottom: 24 }} onClick={onCreateTripClick}>
                            + Create a trip
                        </Button>

                        {selectTrip == null && selectCity == null && (
                            <TripList trips={trips} onTripClick={onTripClick} />
                        )}

                        {selectTrip != null && (
                            <TripDetail
                                selectTrip={selectTrip}
                                selectSite={onSiteClick}
                                tripsites={tripsites}
                                onBack={onBack}
                            />
                        )}

                        {selectTrip == null && selectCity == null && (
                            <div>
                                <div style={{ fontSize: 14, marginTop: 24, marginBottom: 8 }}>
                                    List of Cities
                                </div>
                                {cities.map(city => (
                                    <Button
                                        key={city.id}
                                        block
                                        style={{
                                            textAlign: "center",
                                            marginBottom: 8,
                                            background: "#7f8c8d",
                                            color: "#fff",
                                            border: "none",
                                        }}
                                        onClick={() => onCityClick(city)}
                                    >
                                        {city.name}
                                    </Button>
                                ))}
                            </div>
                        )}

                        {selectCity != null && (
                            <CityDetail
                                selectCity={selectCity}
                                selectSite={onSiteClick}
                                citySites={selectedCitySites}
                                onBack={onCityBack}
                            />
                        )}
                    </>
                )}
            </div>
        );
    }
}

export default TripSider;