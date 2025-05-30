import React, { useState, useEffect } from "react";
import { List, Button, Collapse, Checkbox, message } from "antd";
import dayjs from "dayjs";
import { getSitesByCityId, createTrip, assignSitesToTrip } from "../utils";

const CreateTripDetail = ({ city, tripDates, goToPreviousStep, onCancel }) => {
    const [siteList, setSiteList] = useState([]);
    const [days, setDays] = useState([]);
    const [selectedSitesPerDay, setSelectedSitesPerDay] = useState({});


  useEffect(() => {
    const fetchSites = async () => {
        try {
            const data = await getSitesByCityId(city.id);
            setSiteList(data.map(site => ({ ...site })));
        } catch (err) {
            message.error("Failed to load sites.");
        }
    };

    fetchSites();
    const numDays = dayjs(tripDates[1]).diff(dayjs(tripDates[0]), 'day') + 1;// total days
    const dayList = Array.from({ length: numDays }, (_, i) =>
        dayjs(tripDates[0]).add(i, "day").format("YYYY-MM-DD")
    );
    setDays(dayList);
    const initialSelection = {};
    dayList.forEach(date => {
        initialSelection[date] = [];
    });
    setSelectedSitesPerDay(initialSelection);

  }, [city, tripDates]);
    
    const handleSiteToggle = (date, site) => {
        setSelectedSitesPerDay((prev) => {
            const currentDaySites = prev[date] || [];
            const alreadySelected = currentDaySites.find((s) => s.id === site.id);
            const updatedDaySites = alreadySelected
                ? currentDaySites.filter((s) => s.id !== site.id)
                : [...currentDaySites, site];
            return {
                ...prev,
                [date]: updatedDaySites,
            };
        });
    };


    const handleFinish = async () => {
    try {
        const tripId = await createTrip({
        cityId: city.id,
        tripStartDate: tripDates[0].format("YYYY-MM-DD"),
        tripEndDate: tripDates[1].format("YYYY-MM-DD"),
        });

        const siteAssignments = Object.entries(selectedSitesPerDay).flatMap(([date, sites]) =>
        sites.map((site) => ({
            tripId,
            siteId: site.id,
            visitDate: date,
        }))
        );

        await assignSitesToTrip(tripId, siteAssignments);
        message.success("Trip created successfully!");
        onCancel(); // go back to user home page
    } catch (err) {
        console.error(err);
        message.error("Failed to create or assign sites.");
    }
    };


  return (
      <div style={{ marginTop: 24 }}>
          <h2>All Sites in {city.name}</h2>
        <Collapse accordion>
            {siteList.map((site) => (
            <Collapse.Panel header={site.name} key={site.id}>
                <p><strong>Description:</strong> {site.description || "N/A"}</p>
                <p><strong>Address:</strong> {site.address || "N/A"}</p>
                <p><strong>Rating:</strong> {site.rating || "N/A"}</p>
                    <p><strong>Visit time:</strong> {site.visitTime || "N/A"} mins</p>
                    <img
                        src={site.imageUrls[0]}
                        alt={site.name}
                        style={{ width: "100%", borderRadius: 4, marginTop: 4 }}
                      />
            </Collapse.Panel>
            ))}
          </Collapse>
          
        <h2>Select Sites for Each Day</h2>
          <Collapse accordion>
            {days.map((date, i) => (
                <Collapse.Panel header={`Day ${i + 1} - ${date}`} key={date}>
                    <List
                    dataSource={siteList}
                    renderItem={(site) => (
                        <List.Item>
                        <Checkbox
                            checked={selectedSitesPerDay[date]?.some((s) => s.id === site.id)}
                            onChange={() => handleSiteToggle(date, site)}
                        >
                            {site.name}
                        </Checkbox>
                        </List.Item>
                    )}
                    />
                </Collapse.Panel>
            ))}
          </Collapse>
        <div style={{ marginTop: 24, display: "flex", gap: 8 }}>
            <Button onClick={goToPreviousStep}>Back</Button>
            <Button danger onClick={onCancel}>Cancel</Button>
            <Button type="primary" onClick={handleFinish}>Finish</Button>
        </div>
    </div>
  );
};

export default CreateTripDetail;
