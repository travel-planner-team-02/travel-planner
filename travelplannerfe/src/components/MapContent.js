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

function MapContent({ selectTrip}) {
  const mapRef = useRef(null);
  const [isMapReady, setIsMapReady] = useState(false);

  const onLoad = useCallback((map) => {
    mapRef.current = map;
    setIsMapReady(true);
    console.log("Google Map loaded");
  }, []);

  const location = selectTrip?.cityInfo?.location
    ? {
        lat: selectTrip.cityInfo.location.lat,
        lng: selectTrip.cityInfo.location.lon, 
      }
    : usCenter;

  useEffect(() => {
    if (!isMapReady) return;

    if (selectTrip?.cityInfo?.location && mapRef.current) {
      mapRef.current.panTo(location);
      mapRef.current.setZoom(12);
    } else if (mapRef.current) {
      mapRef.current.panTo(usCenter);
      mapRef.current.setZoom(4);
    }
  }, [selectTrip, isMapReady]);

  return (
    <LoadScript
      googleMapsApiKey={google_map_api_key}
    >
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={usCenter}
        zoom={4}
        onLoad={onLoad}
      >
        {selectTrip && <Marker position={location} />}
      </GoogleMap>
    </LoadScript>
  );
}

export default MapContent;
