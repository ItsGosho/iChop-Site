import React, {Component} from 'react';
import PropTypes from 'prop-types';


/*TODO:Да споделя решението си тук:*/

/*https://stackoverflow.com/questions/980855/inputting-a-default-image-in-case-the-src-attribute-of-an-html-img-is-not-vali*/

class Image extends Component {

    constructor(props) {
        super(props);

        this.state = {
            url: '',
            defaultUrl: '',
            imageUrl: ''
        };

        this.onImageError = this.onImageError.bind(this);

        this.customImageRef = React.createRef();
    }

    onImageError(event) {
        event.target.onerror = null;
        event.target.src = this.state.defaultUrl;
    }

    componentDidMount() {
        let {url, defaultUrl} = this.props;

        this.setState({url, defaultUrl, imageUrl: defaultUrl});
        this.setState({imageUrl: this.state.url})

    }

    render() {
        let {imageUrl} = this.state;
        let {className, style, title} = this.props;

        return (
            <img src={imageUrl}
                 onError={this.onImageError}
                 onLoad={this.onLoad}
                 alt=''
                 title={title}
                 style={style}
                 ref={this.customImageRef}
                 className={className}/>
        );
    }

}

export default Image;


Image.propTypes = {
    url: PropTypes.string,
    defaultUrl: PropTypes.string,
    className: PropTypes.string,
    style: PropTypes.object,
    title: PropTypes.string,
};