import React, { Component ,Fragment} from 'react';
import {List, Button, Col,Card} from 'antd';
import Moment from "moment";


class LightningDetail extends Component {

  componentDidMount(){

  }

  render() {
    console.log("details",this.props.lightningList)
    Moment.locale('tr');
    return (
      <Fragment>
        <Card  title="Submitted Presentation List">
          <List
            loading={this.props.isLoading}
            bordered
            dataSource={this.props.lightningList}
            renderItem={(item, i) => (<List.Item>
              <Col span={5}> {Moment(item['starting']).format("hh:mm")} - {Moment(item['finishing']).format("hh:mm")} </Col>
              <Col span={14}> {item.subject} </Col>
              <Col span={5} push={3}> {item.duration} min</Col>
            </List.Item>)}
          />
        </Card>
      </Fragment>
    );
  }
}

export default LightningDetail;
