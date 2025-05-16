import React from "react";
import { Layout } from "antd";
import { useState, useEffect } from "react";
import TripSider from "./TripSider";
import MapContent from "./MapContent";
import { getTrips, getCityInfoByCityId } from "../utils";

const { Sider } = Layout;


function UsertHomePage({ username }) { 
    const [trips, setTrips] = useState([]);

    useEffect(() => {
        const fetchTrips = async () => {
            const baseTrips = await getTrips(); 

            const enriched = await Promise.all(
                baseTrips.map(async (trip) => {
                    try {
                        const cityInfo = await getCityInfoByCityId(trip.cityId);
                        return { ...trip, cityName: cityInfo.name, cityInfo };
                    } catch (err) {
                        console.error(`Failed to fetch city info for ${trip.cityId}`, err);
                        return { ...trip, cityName: "Unknown" };
                    }
                })
            );

            setTrips(enriched);
        };

        fetchTrips();
    }, []);

    return (
        <Layout style={{ minHeight: "100vh", }}>
            <Sider width={500}>
                <TripSider username={username} trips={ trips} />
            </Sider>
            <Layout style={{ padding: "24px", height: "100vh", background: "#eef2f5"}}>
                <MapContent />
            </Layout>
        </Layout>
    )
}

export default UsertHomePage;