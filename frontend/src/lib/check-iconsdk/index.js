const DELAY = 2000
const TIMEOUT = 2000

function wait(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

export function checkHasIconsdkWallet(timeout) {
    return new Promise(resolve => {
        let _setTimeout = null;
        const eventHandler = event => {
            const { payload, type } = event.detail;
            if (type === 'RESPONSE_HAS_ACCOUNT') {
                clearTimeout(_setTimeout);
                window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
                resolve({
                    iconsdkInstalled: true,
                    hasIconWallet: !!payload.hasAccount
                });
            }
        };
        window.addEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
        window.dispatchEvent(new CustomEvent("ICONSDK_RELAY_REQUEST", { detail: { type: "REQUEST_HAS_ACCOUNT" } }));
        _setTimeout = setTimeout(() => {
            window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
            resolve({ iconsdkInstalled: false, hasIconWallet: false });
        }, timeout);
    });
}

export function checkHasAddress(address, timeout = TIMEOUT) {
    return new Promise(resolve => {
        if (!window.chrome || !address) {
            resolve(false);
        }
        else {
            let _setTimeout = null;
            const eventHandler = event => {
                const { payload, type } = event.detail;
                if (type === 'RESPONSE_HAS_ADDRESS') {
                    window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
                    clearTimeout(_setTimeout);
                    resolve(!!payload.hasAddress);
                }
            };
            window.addEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
            window.dispatchEvent(new CustomEvent("ICONSDK_RELAY_REQUEST", { detail: { type: "REQUEST_HAS_ADDRESS" } }));
            _setTimeout = setTimeout(() => {
                window.removeEventListener("ICONSDK_RELAY_RESPONSE", eventHandler, false);
                resolve(false);
            }, timeout);
        }
    });
}

export default async function (delay = DELAY, timeout = TIMEOUT) {
    if (!window.chrome) {
        return {
            isChrome: false,
            iconsdkInstalled: false,
            hasIconWallet: false,
        }
    }

    await wait(delay)

    const { iconsdkInstalled, hasIconsdkWallet } = await checkHasIconsdkWallet(timeout)

    return {
        isChrome: true,
        iconsdkInstalled,
        hasIconsdkWallet,
    }
}