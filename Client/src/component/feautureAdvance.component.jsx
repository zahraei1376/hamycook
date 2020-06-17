import React from "react";
// ////////////////////////////
import '../sass/components/feautureAdvance.styles.scss';

const FeautureAdvance =({img , text, title}) =>(
    <div className='feauture'>
        <div className="feauture__text">
            <h3 className='feauture__text-heading'>{title}</h3>
            <p className="feauture__text-explain">{text}</p>
        </div>
        <img className='feauture__img' src={img} alt='عکس ویژگی ها' />
    </div>
);

export default FeautureAdvance;