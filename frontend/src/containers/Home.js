import React, {Component, Fragment} from 'react';
import {connect} from 'react-redux';
import {Row, Col} from 'antd';

import LightningDetail from "../components/LightningDetail";
import LightningList from "../components/LightningList";
import LightningForm from "../components/LightningForm";

import {addLightning, getLightning, addLocalLightningList,removeLocalLightningList} from '../redux/lightning'

class Home extends Component {

  componentDidMount(){
    this.props.getLightning();
  }

  handleAddLightning = lightning => {
    this.props.addLocalLightningList(lightning)
  };

  handleRemoveLightning = index => {
    this.props.removeLocalLightningList(index)
  };
  handleSubmitList=lightningList=>{
    this.props.addLightning(lightningList);
    this.props.getLightning();

  };

  render() {
    return (
      <Fragment >
        <Row gutter={30} style={{padding:"30px"}}>
          <Col span={12}>
            <LightningForm handleAddLightning={this.handleAddLightning}/>
          </Col>

          <Col span={12}>
            <LightningList handleRemoveLightning={this.handleRemoveLightning}
                          localLightningList={this.props.localLightningList}
                           handleSubmitList={this.handleSubmitList}/>
          </Col>
        </Row>

        <LightningDetail lightningList={this.props.lightningList}/>
        <p style={{float: "right", padding: "10px"}}><a href={"https://github.com/mrvkrbdk/presentation-java"}>
          Github Repository
        </a> </p>
      </Fragment>
    );
  }
}

const mapStateToProps = ({lightning}) => ({
  lightningList: lightning.lightningList,
  localLightningList: lightning.localLightningList
});

const mapDispatchToProps = {
  addLightning,
  getLightning,
  addLocalLightningList,
  removeLocalLightningList
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);
