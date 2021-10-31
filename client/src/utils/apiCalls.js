import useAxios from "./useAxios";

/** Gọi API mẫu:
    export function xxxxxx() {
        const { response, isLoading } = useAxios({
            method: 'xxxxxx',
            url: "xxxxxx",
            data: {xxxxxx},
            params:{xxxxxxx}
        })
        return {
            xxxxx: response,
            isLoading: isLoading
        }
    }
 */
export function useGetAllUsers() {
    const { response, isLoading } = useAxios({
        method: 'get',
        url: "/api/users/list",
        data: {}
    })
    return {
        data: response,
        isLoading: isLoading
    }
}
export function useGetLyric2() {
    const { response, isLoading } = useAxios({
        method: 'get',
        url: "https://api.lyrics.ovh/v1/Coldplay/Fix You",
        data: {}
    })
    return {
        lyrics: response?.lyrics,
        isLoading: isLoading
    }
}