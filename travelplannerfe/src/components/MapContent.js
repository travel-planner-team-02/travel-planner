import React, { useEffect, useRef, useCallback, useState } from "react";
import { GoogleMap, LoadScript, Marker } from "@react-google-maps/api";

const google_map_api_key = process.env.REACT_APP_GOOGLE_MAPS_API_KEY;

const containerStyle = {
  width: "100%",
  height: "100%",
};

const usCenter = {
  lat: 39.8283,
  lng: -98.5795,
};

function MapContent({ selectTrip, selectSite }) {
  const mapRef = useRef(null);
  const [isMapReady, setIsMapReady] = useState(false);

  const onLoad = useCallback((map) => {
    mapRef.current = map;
    setIsMapReady(true);
    console.log("Google Map loaded");
  }, []);

  const cityLocation = selectTrip?.cityInfo?.location
    ? {
        lat: selectTrip.cityInfo.location.lat,
        lng: selectTrip.cityInfo.location.lon,
      }
    : usCenter;

  const siteLocation = selectSite?.location
    ? {
        lat: selectSite.location.lat,
        lng: selectSite.location.lon,
      }
    : null;

  useEffect(() => {
    if (!isMapReady) return;

    if (cityLocation && mapRef.current) {
      mapRef.current.panTo(cityLocation);
      mapRef.current.setZoom(12);
    } else if (mapRef.current) {
      mapRef.current.panTo(usCenter);
      mapRef.current.setZoom(4);
    }
  }, [selectTrip, isMapReady, cityLocation]);

  useEffect(() => {
    if (!isMapReady || !siteLocation || !mapRef.current) return;

    mapRef.current.panTo(siteLocation);
    mapRef.current.setZoom(15);
  }, [selectSite, isMapReady, siteLocation]);

  return (
    <LoadScript googleMapsApiKey={google_map_api_key}>
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={usCenter}
        zoom={4}
        onLoad={onLoad}
      >

        {selectSite && <Marker position={siteLocation} />}
      </GoogleMap>
    </LoadScript>
  );
}

export default MapContent;
