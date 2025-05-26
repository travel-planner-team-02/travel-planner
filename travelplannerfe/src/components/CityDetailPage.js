import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getCityInfoByCityId, getSitesByCityId } from "../utils";

const CityDetailPage = () => {
    const { cityId } = useParams();
    const [city, setCity] = useState(null);
    const [sites, setSites] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchCityData = async () => {
            try {
                const cityInfo = await getCityInfoByCityId(cityId);
                const siteList = await getSitesByCityId(cityId);
                setCity(cityInfo);
                setSites(siteList);
            } catch (err) {
                setError("Failed to load city details.");
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchCityData();
    }, [cityId]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;
    if (!city) return <div>City not found</div>;

    return (
        <div style={{ padding: "1rem" }}>
            <h1>{city.name}</h1>
            <p>{city.description}</p>
            <h2>Sites in {city.name}</h2>
            {sites.length === 0 ? (
                <p>No sites found.</p>
            ) : (
                <ul>
                    {sites.map((site) => (
                        <li key={site.id}>
                            <strong>{site.name}</strong> - {site.description}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default CityDetailPage;