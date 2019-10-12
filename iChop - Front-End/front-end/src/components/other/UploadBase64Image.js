import React, {Component, Fragment} from 'react';

class UploadBase64Image extends Component {

    constructor(props) {
        super(props);

        this.onImageUpload = this.onImageUpload.bind(this);
        this.getBase64 = this.getBase64.bind(this);

        this.inputFileRef = React.createRef();
    }


    onImageUpload(event) {

        let file = event.target.files[0];

        let type = file.type; /*image/png*/
        let size = file.size;

        if (type === 'image/png') {

            if (size > 1048576) {
                /*TODO: show error*/
                return;
            }

            this.getBase64(file).then(data => {
                this.props.onUpload(data);
            });

        } else {
            /*TODO: show error*/
        }
    }

    getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                let encoded = reader.result.replace(/^data:(.*;base64,)?/, '');
                if ((encoded.length % 4) > 0) {
                    encoded += '='.repeat(4 - (encoded.length % 4));
                }
                resolve(encoded);
            };
            reader.onerror = error => reject(error);
        });
    }

    render() {

        return (
            <Fragment>
                <button type="button"
                        className="btn btn-warning btn-sm"
                        onClick={() => {
                            this.inputFileRef.current.click()
                        }}>
                    Choose
                </button>

                <input type="file"
                       className="dont-display"
                       ref={this.inputFileRef}
                       onChange={this.onImageUpload}/>
            </Fragment>
        );
    }
}

export default UploadBase64Image;