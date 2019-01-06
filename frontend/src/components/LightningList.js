import React, {Component, Fragment} from 'react';
import {List, Button, Col,Card,Icon} from 'antd';


class LightningList extends Component {

  handleRemove = index => {
    console.log(index)
    this.props.handleRemoveLightning(index)
  }
  handleSubmitList=(lightningList)=>{
    this.props.handleSubmitList(lightningList);
  }

  render() {
    return (
      <Fragment>
        <Card title="Planned Presentation List">
        <List
          bordered
          dataSource={this.props.localLightningList}
          renderItem={(item, i) => (<List.Item>
            <Col span={16}> {item.subject} </Col>
            <Col span={6}> {item.duration} min</Col>
            <Col span={2} offset={0}>
              {/*<Button type='danger'  onClick={e => this.handleRemove(i)}><Icon type="delete" /></Button>*/}
              <Icon type="delete" style={{color:"red"}} onClick={e => this.handleRemove(i)} />
            </Col>
          </List.Item>)}
        />
          <Button style={{margin:"2% 5% 0% 50%"}} onClick={e=>this.handleSubmitList(this.props.localLightningList)}>Submit</Button>
        </Card>
      </Fragment>
    );
  }
}

export default LightningList;
