import React, { Component } from 'react';

class BtpMessage extends Component {

    render() {
        const {
            data
        } = this.props
        const {
            source,
            destination,
            service,
            sequenceNumber,
            payload
        } = data

        return ([
            <h1 key="h1" className="title">BTP Message</h1>,
            <div key="div" className="scroll">
                <p className="label">Source</p>
                <p className="txt">{source}</p>
                <p className="label">Destination</p>
                <p className="txt">{destination}</p>
                <p className="label">Service</p>
                <p className="txt">{service}</p>
                <p className="label">Seq</p>
                <p className="txt">{sequenceNumber}</p>
                <p className="label">Payload</p>
                <p className="txt">{payload}</p>
            </div>
        ])
    }
}

export default BtpMessage
