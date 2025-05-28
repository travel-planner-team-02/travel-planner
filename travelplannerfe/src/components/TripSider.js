import React from "react";
import { Button } from "antd";
import TripList from "./TripList";
import TripDetail from "./TripDetail";
import CityDetail from "./CityDetail";

class TripSider extends React.Component {


    render() {

        return (
            <div style={{ width: 500, background: "#2c3e50", color: "#fff", height: "100%", padding: 16 }}>
                <div style={{ fontWeight: 600, fontSize: 18, marginBottom: 16 }}>
                    Hi {this.props.username}!
                </div>

                <Button type="primary" shape="round" block style={{ marginBottom: 24 }}>
                    + Create a trip
                </Button>

                {this.props.selectTrip == null && this.props.selectCity == null && (
                    <TripList
                        trips={this.props.trips}
                        onTripClick={this.props.onTripClick}
                    />
                )}

                {this.props.selectTrip != null && (
                    <TripDetail
                        selectTrip={this.props.selectTrip}
                        selectSite={this.props.onSiteClick}
                        tripsites={this.props.tripsites}
                        onBack={this.props.onBack}
                    />
                )}

                {this.props.selectTrip == null && this.props.selectCity == null && (
                    <div>
                        <div style={{ fontSize: 14, marginTop: 24, marginBottom: 8 }}>
                            List of Cities
                        </div>

                        {this.props.cities.map(city => (
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
                                onClick={() => this.props.onCityClick(city)}
                            >
                                {city.name}
                            </Button>
                        ))}
                    </div>
                )}

                {this.props.selectCity != null && (
                    <CityDetail
                        selectCity={this.props.selectCity}
                        selectSite={this.props.onSiteClick}
                        citySites={this.props.selectedCitySites}
                        onBack={this.props.onCityBack}
                    />
                )}
            </div>
        );
    }
}

export default TripSider;