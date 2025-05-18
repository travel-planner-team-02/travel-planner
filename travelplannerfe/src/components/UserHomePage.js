import React from "react";
import { Layout } from "antd";
import { useState, useEffect } from "react";
import TripSider from "./TripSider";
import MapContent from "./MapContent";
import { getTrips, getCityInfoByCityId, getTripDetailByTripId } from "../utils";

const { Sider } = Layout;


function UsertHomePage({ username, authed }) { 
    const [trips, setTrips] = useState([]);
    const [selectTrip, setSelectTrip] = useState(null);
    const [tripsites, setTripSites] = useState([]);
    const [selectSite, setSelectSite] = useState(null);

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

    const handleSelectTrip = async (trip) => {
        console.log("selected trip:", trip);
        setSelectTrip(trip);
        try {
            const tripSitesList = await getTripDetailByTripId(trip.id); 
            console.log(tripSitesList);
            setTripSites(tripSitesList);
        } catch (err) {
            console.error("Failed to fetch trip detail", err);
        }
    };

    const hanldeSelectSite = (site) => {
        setSelectSite(site);
        console.log("select site: ", site);
    }

    const handleBack = () => {
        setSelectTrip(null);
        setSelectSite(null);
        setTripSites([]); 
    };


    return (
        <Layout style={{ minHeight: "100vh", }}>
            <Sider width={500}>
                <TripSider
                    username={username}
                    trips={trips}
                    onTripClick={handleSelectTrip}
                    onSiteClick={hanldeSelectSite}
                    selectTrip={selectTrip}
                    tripsites={tripsites}
                    onBack={handleBack}
                />
            </Sider>
            <Layout style={{ padding: "24px", height: "100vh", background: "#eef2f5"}}>
                {authed && <MapContent selectTrip={selectTrip} selectSite={selectSite} />}
            </Layout>
        </Layout>
    )
}

export default UsertHomePage;