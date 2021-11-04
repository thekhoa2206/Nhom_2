import { uniqueId } from "lodash"

const priceReducer = (state, action = {}) => {
    const { type } = action
    console.log("action price", action)
    switch (type) {
        case 'GET_ALL_PRICE':
            return {
                ...state,
                price: action.payload,
            }

        case 'ADD_PRICE':
            return {
                ...state,
                price: [...state.price, action.payload],
            }

        case 'EDIT_PRICE':
            const updatedPrice = action.payload;

            const updatedPrices = state.price.map((price) => {
                if (price.id === updatedPrice.id) {
                    return updatedPrices;
                }
                return price;
            });

            return {
                ...state,
                price: updatedPrice,
            };

        case 'DELETE_PRICE':
            return {
                ...state,
                price: state.price.filter(
                    (p) => p.id !== action.payload
                ),
            };

        default:
            return state
    }
}

export default priceReducer