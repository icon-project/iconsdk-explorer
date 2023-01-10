import { makeUrl, getChainInfo } from 'utils/utils'
import { trackerApiInstance } from './config'

export async function btpList(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl(`/v3/` + getChainInfo() + `/btp/header/list`, payload))
            .then(result => {
                resolve(result.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}

export async function btpInfo(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl(`/v3/` + getChainInfo() + `/btp/header/info`, payload))
        .then(result => {
            resolve(result.data)
        })
        .catch(error => {
            reject(error)
        })
    })
}

/* transactions include btp message */
export async function btpTxList(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl(`/v3/` + getChainInfo() + `/transaction/btp`, payload))
            .then(result => {
                resolve(result.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}