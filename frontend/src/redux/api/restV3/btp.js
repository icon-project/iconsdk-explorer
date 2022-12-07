import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

const chainInfo = () => {
    return localStorage.getItem("chainName");
}

export async function btpList(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl(`/v3/` + chainInfo() + `/btp/header/list`, payload))
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
        trackerApi.get(makeUrl(`/v3/` + chainInfo() + `/btp/header/info`, payload))
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
        trackerApi.get(makeUrl(`/v3/` + chainInfo() + `/transaction/btp`, payload))
            .then(result => {
                resolve(result.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}