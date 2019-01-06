import React, {Component, Fragment} from 'react';
import {Form, Input, Button, Card,Col} from 'antd';

class LightningForm extends Component {

  state = {
    subject: "",
    duration: null,
  };

  handleChange = e => this.setState({
    [e.target.name]: e.target.value
  })

  handleSubmit = e => {
    e.preventDefault();
    let {subject, duration} = this.state;

    if (duration<5){
      duration = 5;
    }
    else if (duration > 60) {
      duration = 60;
    }


    duration = Math.ceil(duration / 5) * 5;
    this.props.handleAddLightning({subject, duration});
    this.setState({subject: "", duration: null});
  };


  render() {
    return (
      <Fragment>
        <Card title="Add Presentation">
          <Form>
            <Col span={16}>
              <Input name="subject" type="text" placeholder="Subject" value={this.state.subject}
                     onChange={this.handleChange}/>
            </Col>
            <Col span={6}>
              <Input name="duration" min={5} max={60} step={5} type="number" placeholder="Duration"
                     value={this.state.duration} onChange={this.handleChange}/>
            </Col>
            <Col span={2}>
              <Button onClick={this.handleSubmit}>Add</Button>
            </Col>
          </Form>
        </Card>
      </Fragment>
    );
  }
}

export default LightningForm;
