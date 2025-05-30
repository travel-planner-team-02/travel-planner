import React from "react";

class CreateTripFlow extends React.Component{

    render() {
        return (
            <div style={{ backgroundColor: "#1abc9c", height: "100%", padding: 24 }}>
                <h2 style={{ color: "white" }}>This is CreateTripFlow</h2>
                <button onClick={this.props.onCancel}>Cancel</button>
            </div>
        );
    }
}

export default CreateTripFlow;