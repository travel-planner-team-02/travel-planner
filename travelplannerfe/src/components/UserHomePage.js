import React from "react";
import { Layout } from "antd";
import { useState, useEffect } from "react";
import TripSider from "./TripSider";
import MapContent from "./MapContent";
import { getTrips, getCityInfoByCityId } from "../utils";

const { Sider } = Layout;


function UsertHomePage({ username, authed }) { 
    const [trips, setTrips] = useState([]);
    const [selectTrip, setSelectTrip] = useState(null);

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
            //console.log("enriched trips: ", enriched);
            setTrips(enriched);
        };

        fetchTrips();
    }, []);

    const handleSelectTrip = (trip) => {
        console.log("selected trip:", trip);
        setSelectTrip(trip);
    };

    return (
        <Layout style={{ minHeight: "100vh", }}>
            <Sider width={500}>
                <TripSider username={username} trips={trips} onTripClick={handleSelectTrip} />
            </Sider>
            <Layout style={{ padding: "24px", height: "100vh", background: "#eef2f5"}}>
                {authed && <MapContent selectTrip={selectTrip} />}
            </Layout>
        </Layout>
    )
}

export default UsertHomePage;