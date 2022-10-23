import { makeUrl } from 'utils/utils'
import { trackerApiInstance } from './config'

export async function searchData(payload) {
    console.log("searchData: ", payload);
    console.log(makeUrl('/v0/search/Search'));
    const trackerApi = await trackerApiInstance()
    return new Promise((resolve, reject) => {
        console.log(payload, makeUrl('/v0/search/Search', payload))
        trackerApi.get(makeUrl('/v0/search/Search', payload))
            .then(result => {
                console.log("RESULT", result);
                resolve(result.data.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}