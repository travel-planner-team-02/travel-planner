import React, { useState } from "react";
import { Button } from "antd";

function CityDetail({ selectCity, selectSite, citySites, onBack }) {
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
                <div style={{ fontSize: 16, fontWeight: 600 }}>{selectCity.name}</div>
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
                {citySites.length === 0 ? (
                    <div>No sites found.</div>
                ) : (
                    <ul style={{ listStyle: "none", padding: 0 }}>
                        {citySites.map((citySites) => (
                            <li
                                key={citySites.id}
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
                                        onClick={() => selectSite(citySites)}
                                    >
                                        {citySites.name}
                                    </span>
                                    <Button
                                        type="link"
                                        size="small"
                                        onClick={() => toggleDetail(citySites.id)}
                                        style={{ color: "#fff", padding: 0 }}
                                    >
                                        {expandedSiteId === citySites.id ? "Hide" : "Details"}
                                    </Button>
                                </div>

                                {expandedSiteId === citySites.id && (
                                    <div style={{ marginTop: 8, fontSize: 13, color: "#ddd" }}>
                                        <p><strong>Description:</strong> {citySites.description}</p>
                                        <p><strong>Address:</strong> {citySites.address}</p>
                                        <p><strong>Rating:</strong> {citySites.rating}</p>
                                        <p><strong>Visit time:</strong> {citySites.visitTime} mins</p>
                                        {citySites.imageUrls?.[0] && (
                                            <img
                                                src={citySites.imageUrls[0]}
                                                alt={citySites.name}
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

export default CityDetail;
