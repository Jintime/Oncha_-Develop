

export async function get (url, data) {
  let unionUrl;
  if (data) {
    unionUrl = url + "?" + new URLSearchParams(data);
  } else {
    unionUrl = url;
  }
  return fetch(unionUrl, {
    credentials : "include"
  })
}


export async function post (url, data) {
  return requestIncludeBody(url, "POST", data);
}

export async function put (url, data) {
  return requestIncludeBody(url, "PUT", data);
}

export async function del (url, data) {
  return requestIncludeBody(url, "delete", data);
}


async function requestIncludeBody (url, method, data) {
  method = method.toUpperCase();

  if (data) {
    return fetch(url, {
      credentials : "include",
      method,
      headers : {
        "Content-Type" : "application/json",
      },
      body : JSON.stringify(data)
    })
  } else {
    return fetch(url, {
      credentials : "include",
      method,
      headers : {
        "Content-Type" : "application/json",
      }
    })
  }
}