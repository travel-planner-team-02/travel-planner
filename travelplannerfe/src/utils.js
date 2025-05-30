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

export const getTrips = () => {
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

export const getCityInfoByCityId = (cityId) => {
    const authToken = localStorage.getItem("authToken");
    const getCityInfoByCityIdURL = `${domain}/cities/${cityId}`;
    return fetch(getCityInfoByCityIdURL, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${authToken}`,
            "Content-Type": "application/json",
        }
    }).then((response) => {
        if (response.status >= 300) {
            throw Error("Failed to fetch city info")
        }
        return response.json();
    });
}

export const getCities = async () => {
    const authToken = localStorage.getItem("authToken");
    const getCityURL = `${domain}/cities`;
    return fetch(getCityURL, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${authToken}`,
            "Content-Type": "application/json",
        }
    }).then((response) => {
        if (response.status >= 300) {
            throw Error("Failed to fetch city info")
        }
        return response.json();
    });
};

export const getSitesByCityId = (cityId) => {
    const authToken = localStorage.getItem("authToken");
    return fetch(`${domain}/cities/${cityId}/sites`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${authToken}`,
            "Content-Type": "application/json",
        }
    }).then((res) => {
        if (res.status >= 300) throw Error("Failed to fetch sites for city");
        return res.json();
    });
};

export const getTripDetailByTripId = (tripId) => {
    const authToken = localStorage.getItem("authToken");
    const getTripDetailByTripIdURL = `${domain}/trips/${tripId}/sites`;
    return fetch(getTripDetailByTripIdURL, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${authToken}`,
            "Content-Type": "application/json",
        }
    }).then((response) => {
        if (response.status >= 300) {
            throw Error("Failed to fetch city info")
        }
        return response.json();
    });
}

export const createTrip = async ({ cityId, tripStartDate, tripEndDate }) => {
    const authToken = localStorage.getItem("authToken");

    return fetch(`${domain}/trips/create`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${authToken}`,
        },
        body: JSON.stringify({
            cityId,
            tripStartDate, //  'YYYY-MM-DD'
            tripEndDate,
        }),
    }).then((res) => {
        if (res.status >= 300) {
            throw new Error("Failed to create trip");
        }
        return res.json(); // return new tripId
    });
};

export const assignSitesToTrip = async (tripId, assignments) => {
  const authToken = localStorage.getItem("authToken");

  const response = await fetch(`${domain}/tripsite/${tripId}/assig-sites`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${authToken}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify(assignments),
  });

  if (!response.ok) {
    throw new Error("Failed to assign sites to trip");
  }
};

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