import axios from "axios"

// Action types
const GET = 'myApp/lightning/GET';
const ADD = 'myApp/lightning/ADD';
const LOCAL_ADD = 'myApp/lightning/LOCAL_ADD';
const LOCAL_REMOVE = 'myApp/lightning/LOCAL_REMOVE';


const initialState = {
  lightningList: [],
  localLightningList: [],
  isLoading: false,
  isError: false
}

// Reducer
export default function reducer(state = initialState, action = {}) {
  switch (action.type) {
    case `${GET}_PENDING`:
      return {
        ...state,
        isLoading: true
      };
    case `${GET}_FULFILLED`:
      return {
        ...state,
        lightningList: [...action.payload],
        isLoading: false,
      };
    case `${GET}_REJECT`:
      return {
        ...state,
        isLoading: false,
        isError: true
      };

    case `${ADD}_PENDING`:
      return {
        ...state,
        isLoading: true
      };
    case `${ADD}_FULFILLED`:
      return {
        ...state,
        isLoading: false,
      };
    case `${ADD}_REJECT`:
      return {
        ...state,
        isLoading: false,
        isError: true
      };

    case LOCAL_ADD:
      return {
        ...state,
        localLightningList: [...state.localLightningList, action.payload],
      };
    case LOCAL_REMOVE:
      return {
        ...state,
        localLightningList: [
          ...state.localLightningList.slice(0, action.payload),
          ...state.localLightningList.slice(action.payload + 1)
        ]
      };

    default:
      return state;
  }
}

// actions
export const addLightning = (lightningList) => dispatch => dispatch({
  type: ADD,
  payload: axios.post("api/lightning", lightningList)
});

export const getLightning = () => dispatch => dispatch({
  type: GET,
  payload: axios.get("api/lightning").then(res => res.data)
});

export const addLocalLightningList = (localLightning) => ({
  type: LOCAL_ADD,
  payload: localLightning
});

export const removeLocalLightningList = (index) => ({
  type: LOCAL_REMOVE,
  payload: index
});
