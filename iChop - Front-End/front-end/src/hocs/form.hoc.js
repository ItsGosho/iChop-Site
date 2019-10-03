import React, {Component} from 'react';

let formHoc = (Comp) => {
    return class extends Component {

        constructor(props) {
            super(props);
            this.state = {};

            this.onChange = this.onChange.bind(this);
        }

        onChange(event) {
            event.preventDefault();
            console.log(event.target);

            let inputName = event.target.name;
            let value = event.target.value;

            this.setState({[inputName]: value});
        }

        render() {
            return (
                <Comp
                    formMethods={
                        {
                            onChange: this.onChange,
                        }
                    }
                    formData={this.state}
                    {...this.props}/>
            );
        }
    }
};

export default formHoc;