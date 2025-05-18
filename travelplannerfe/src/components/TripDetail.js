import React, { useState } from "react";
import { Button } from "antd";

function TripDetail({ selectTrip, selectSite, tripsites, onBack }) {
  const [expandedSiteId, setExpandedSiteId] = useState(null);

  const toggleDetail = (siteId) => {
    setExpandedSiteId((prev) => (prev === siteId ? null : siteId));
  };

  return (
    <div>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          marginBottom: 12,
        }}
      >
        <div>
          <strong>{selectTrip.cityName} Trip</strong>
          <div style={{ fontSize: 12 }}>
            {selectTrip.tripStartDate} - {selectTrip.tripEndDate}
          </div>
        </div>
        <Button size="small" onClick={onBack}>
          Back
        </Button>
      </div>

      <div
        style={{
          maxHeight: "calc(100vh - 160px)", 
          overflowY: "auto",
          paddingRight: 4,
        }}
      >
        {tripsites.length === 0 ? (
          <div>No sites found.</div>
        ) : (
          <ul style={{ listStyle: "none", padding: 0 }}>
            {tripsites.map((tripsite) => (
              <li
                key={tripsite.id}
                style={{
                  background: "#34495e",
                  color: "#fff",
                  padding: "8px 12px",
                  marginBottom: 10,
                  borderRadius: 4,
                }}
              >
                <div style={{ display: "flex", justifyContent: "space-between" }}>
                  <span
                    style={{ cursor: "pointer" }}
                    onClick={() => selectSite(tripsite)}
                  >
                    {tripsite.name}
                  </span>
                  <Button
                    type="link"
                    size="small"
                    onClick={() => toggleDetail(tripsite.id)}
                    style={{ color: "#fff", padding: 0 }}
                  >
                    {expandedSiteId === tripsite.id ? "Hide" : "Details"}
                  </Button>
                </div>

                {expandedSiteId === tripsite.id && (
                  <div style={{ marginTop: 8, fontSize: 13, color: "#ddd" }}>
                    <p><strong>Visit Date:</strong> {tripsite.visitDate}</p>
                    <p><strong>Description:</strong> {tripsite.description}</p>
                    <p><strong>Address:</strong> {tripsite.address}</p>
                    <p><strong>Rating:</strong> {tripsite.rating}</p>
                    <p><strong>Visit time:</strong> {tripsite.visitTime} mins</p>
                    {tripsite.imageUrls?.[0] && (
                      <img
                        src={tripsite.imageUrls[0]}
                        alt={tripsite.name}
                        style={{ width: "100%", borderRadius: 4, marginTop: 4 }}
                      />
                    )}
                  </div>
                )}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default TripDetail;
