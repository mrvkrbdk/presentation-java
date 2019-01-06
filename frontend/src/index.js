import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from "react-redux";
import { PersistGate } from 'redux-persist/integration/react';

import App from './App';

import './index.css';
import 'antd/dist/antd.css';
import {store, persistor} from "./redux/store";

const Main = () => (
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <App/>
    </PersistGate>
  </Provider>
);

ReactDOM.render(<Main/>, document.getElementById('root'));

