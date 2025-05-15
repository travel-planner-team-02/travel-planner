import React from "react";
import { Button } from "antd";

function TripList({ trips, onTripClick }) {
    return (
        <div >
            <div style={{ fontSize: 14, marginBottom: 8 }}>
                Your trips
            </div>
            
            {trips.length === 0 ? (
                <div style={{ color: "gray", fontStyle: "italic", display:"flex", justifyContent: "center" }}>
                    No trips yet
                </div>
                ) : (
                 trips.map((trip) => (
                     <Button key={trip.id} block
                        style={{
                            textAlign: "center",
                            marginBottom: 8,
                            background: "#34495e",
                            color: "#fff",
                            border: "none",
                         }}
                        onClick={() => onTripClick(trip)}
                     >
                        {trip.cityId}
                    </Button>
    ))
)}
        </div>
    );
}

export default TripList;