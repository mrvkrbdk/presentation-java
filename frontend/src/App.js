import React, {Component, Fragment} from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';

import Home from "./containers/Home";
import NotFound from "./containers/NotFound";


class App extends Component {
  render() {
    return (
      <Fragment>
        <BrowserRouter>
          <Switch>
            <Route path="/" component={Home} exact/>
            <Route component={NotFound}/>
          </Switch>
        </BrowserRouter>
      </Fragment>
    );
  }
}

export default App;
