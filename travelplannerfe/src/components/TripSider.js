import React from "react";
import { Button } from "antd";
import TripList from "./TripList";
import TripDetail from "./TripDetail";

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

                { this.props.selectTrip == null ? (
                    <TripList
                        trips={this.props.trips}
                        onTripClick={this.props.onTripClick}
                    />
                ) : (
                        <TripDetail
                            selectTrip={this.props.selectTrip}
                            selectSite = {this.props.onSiteClick}
                            tripsites={this.props.tripsites}
                            onBack={this.props.onBack}
                        />
                    )
                }
        </div>
        );
    }
}

export default TripSider;