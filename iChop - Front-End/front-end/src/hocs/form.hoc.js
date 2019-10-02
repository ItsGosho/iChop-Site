import React, {Component} from 'react';

let formHoc = (Comp) => {
    return class extends Component {

        constructor(props) {
            super(props);
            this.state = {};

            this.onChange = this.onChange.bind(this);
        }

        onChange(event) {
            let inputName = event.target.name;
            let value = event.target.value;

            this.setState({[inputName]: value});
            event.preventDefault();
        }

        render() {
            return (
                <Comp
                    formMethods={
                        {
                            onChange: this.onChange
                        }
                    }
                    formData={this.state}
                    {...this.props}/>
            );
        }
    }
};

export default formHoc;