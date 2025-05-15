const domain = "http://localhost:8080"

export const login = (credential) => { 
    const loginUrl = `${domain}/auth/login`;
    const networkRequestStatus = fetch(loginUrl,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(credential)
        }
    );
    return networkRequestStatus.then((response) => { 
        if (response.status >= 300) { 
            throw Error("Fail to log in");
        }

        return response.json();
    });
}

export const register = (credential) => { 
    const registerUrl = `${domain}/auth/register`;
    return fetch(registerUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credential)
    }).then((response) => {
        if (response.status >= 300) {
            throw Error("Fail to register");
        }
        return response.json(); // return 给上层
    });
}

export const getTripsByUserId = () => {
    const authToken = localStorage.getItem("authToken");
    const listTripssURL = `${domain}/trips`;

    return fetch(listTripssURL,
        {
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        }).then((response) => {
            if (response.status >= 300) {
                throw Error("Fail to get trip list")
            }
            return response.json();
        })
}

// parse the tkoen to get username
export function parseJwt(token) {
    try {
        const base64Payload = token.split(".")[1];
        const decoded = JSON.parse(atob(base64Payload));
        return decoded;
    } catch (e) {
        return null;
    }
}