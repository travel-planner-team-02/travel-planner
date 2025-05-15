import React from "react";
import { Layout } from "antd";
import TripSider from "./TripSider";
import MapContent from "./MapContent";

const { Sider } = Layout;

function UsertHomePage({ username }) { 
    console.log("username: ", username);
    return (
        <Layout style={{ minHeight: "100vh", }}>
            <Sider width={500}>
                <TripSider username={username}/>
            </Sider>
            <Layout style={{ padding: "24px", height: "100vh", background: "#eef2f5"}}>
                <MapContent />
            </Layout>
        </Layout>
    )
}

export default UsertHomePage;