import React from "react";
import { Layout } from "antd";
import { useState, useEffect } from "react";
import TripSider from "./TripSider";
import MapContent from "./MapContent";
import { getCities, getTrips, getSitesByCityId, getCityInfoByCityId, getTripDetailByTripId } from "../utils";

const { Sider } = Layout;

function UsertHomePage({ username, authed }) {
    const [trips, setTrips] = useState([]);
    const [selectTrip, setSelectTrip] = useState(null);
    const [tripsites, setTripSites] = useState([]);
    const [selectSite, setSelectSite] = useState(null);
    const [cities, setCities] = useState([]);
    const [selectCity, setSelectCity] = useState(null);
    const [selectedCitySites, setSelectedCitySites] = useState([]);

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

    useEffect(() => {
        const fetchCities = async () => {
            try {
                const citiesFromServer = await getCities();
                setCities(citiesFromServer);
            } catch (err) {
                console.error("Failed to fetch cities", err);
            }
        };

        fetchCities();
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

    const handleBackCities = () => {
        setSelectCity(null);
        setSelectSite(null);
        setSelectedCitySites([]);
    };

    const handleSelectCity = async (city) => {
        console.log("selected city:", city);
        console.log("city id: ", city.id);
        setSelectCity(city);
        try {
            const citySitesList = await getSitesByCityId(city.id);
            console.log(citySitesList);
            setSelectedCitySites(citySitesList);
        } catch (err) {
            console.error("Failed to fetch city detail", err);
        }
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
                    cities={cities}
                    selectCity={selectCity}
                    selectedCitySites={selectedCitySites}
                    onCityClick={handleSelectCity}
                    onCityBack={handleBackCities}
                />
            </Sider>
            <Layout style={{ padding: "24px", height: "100vh", background: "#eef2f5" }}>
                {authed && <MapContent selectTrip={selectTrip} selectSite={selectSite} />}
            </Layout>
        </Layout>
    )
}

export default UsertHomePage;