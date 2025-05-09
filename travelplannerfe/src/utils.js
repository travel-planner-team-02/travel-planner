const domin = "http://localhost:8080"

export const login = (credential) => { 
    // const loginUrl = `${domin}/auth/login`;
    // const networkRequestStatus = fetch(loginUrl,
    //     {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": " application/json"
    //         },
    //         body: JSON.stringify(credential)
    //     }
    // );
    // networkRequestStatus.then((response) => { 
    //     if (response.status >= 300) { 
    //         throw Error("Fail to log in");
    //     }

    //     return response.json();
    // });
    return Promise.resolve({
        token: "mock-token-123",
        username: credential.username
      });
}

export const register = (credential) => { 
  // const loginUrl = `${domin}/auth/login`;
  // const networkRequestStatus = fetch(loginUrl,
  //     {
  //         method: "POST",
  //         headers: {
  //             "Content-Type": " application/json"
  //         },
  //         body: JSON.stringify(credential)
  //     }
  // );
  // networkRequestStatus.then((response) => { 
  //     if (response.status >= 300) { 
  //         throw Error("Fail to log in");
  //     }

  //     return response.json();
  // });
  return Promise.resolve({
      token: "mock-token-123",
      username: credential.username
    });
}