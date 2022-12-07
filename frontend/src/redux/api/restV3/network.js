import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

const chainInfo = () => {
    return localStorage.getItem("chainName");
}

export async function networkList(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl(`/v3/` + chainInfo() + `/btp/network/list`, payload))
            .then(result => {
                resolve(result.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}

export async function networkInfo(payload) {
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        trackerApi.get(makeUrl(`/v3/` + chainInfo() + `/btp/network/info`, payload))
            .then(result => {
                resolve(result.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}

export async function networkBTPList(payload) {
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