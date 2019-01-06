import {createStore, compose, applyMiddleware, combineReducers} from 'redux';
import {persistStore, persistReducer} from 'redux-persist';
import thunk from 'redux-thunk';
import reduxPromise from 'redux-promise-middleware';
import storage from 'redux-persist/lib/storage'

import lightningReducer from './lightning'

const persistConfig = {
  key: 'n13',
  storage,
};

const rootReducer = combineReducers({
  lightning: lightningReducer
});

let middlewares = [reduxPromise(), thunk];

let composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const getMiddlewares = () => applyMiddleware(...middlewares);

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = createStore(persistedReducer, undefined, composeEnhancers(getMiddlewares()));

const persistor = persistStore(store);

export {store, persistor};
