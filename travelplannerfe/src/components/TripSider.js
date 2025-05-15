import React from "react";
import { Button } from "antd";
import { getTripsByUserId } from "../utils";
import TripList from "./TripList";

class TripSider extends React.Component {
    state = {
        trips: [],
    };

    componentDidMount() {
        getTripsByUserId(this.props.username).then((trips) => {
        this.setState({ trips });
        });
    }

    handleTripClick = (trip) => {
        console.log("clicked trip ", trip);
    };

    render() {
        const { trips } = this.state;

        return (
        <div style={{ width: 500, background: "#2c3e50", color: "#fff", height: "100%", padding: 16 }}>
            <div style={{ fontWeight: 600, fontSize: 18, marginBottom: 16 }}>
                Hi {this.props.username}!
            </div>

            <Button type="primary" shape="round" block style={{ marginBottom: 24 }}>
                + Create a trip
            </Button>

            <TripList trips={trips} onTripClick={this.handleTripClick} />
        </div>
        );
    }
}

export default TripSider;