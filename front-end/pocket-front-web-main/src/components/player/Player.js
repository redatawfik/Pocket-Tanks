import React from 'react';
import PropTypes from 'prop-types';
import Score from '../score/Score.js';
import './Player.css';

const Player = (props) => {
  return (
    <div className="player clearfix">
      <div className="player-name">
        {props.name}
      </div>
      <Score score={props.score}/>
    </div>
  )
};

Player.propTypes = {
  name: PropTypes.string.isRequired,
  score: PropTypes.number.isRequired
}

export default Player;