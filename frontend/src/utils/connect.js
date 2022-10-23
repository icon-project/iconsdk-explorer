export function requestAddress() {
    return new Promise(resolve => {
      window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
      window.addEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
      window.dispatchEvent(
        new CustomEvent("ICONSDK_RELAY_REQUEST", {
          detail: {
            type: "REQUEST_ADDRESS"
          }
        })
      );
      function eventHandler(event) {
        const { type, payload } = event.detail;
        if (type === "RESPONSE_ADDRESS") {
          window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
          resolve(payload);
        }
      }
    });
  }
  export function requestJsonRpc(rawTransaction) {
    return new Promise(resolve => {
      window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
      window.addEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
      window.dispatchEvent(
        new CustomEvent("ICONSDK_RELAY_REQUEST", {
          detail: {
            type: "REQUEST_JSON-RPC",
            payload: {
              jsonrpc: "2.0",
              method: "icx_sendTransaction",
              params: rawTransaction,
              id: 50889
            }
          }
        })
      );
      function eventHandler(event) {
        const { type, payload } = event.detail;
        if (type === "RESPONSE_JSON-RPC") {
          window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
          resolve(payload);
        }
      }
    });
  }
  