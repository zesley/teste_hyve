<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="imgs/favicon.ico" />
<title>bol Mancala Test</title>
<link rel="stylesheet" type="text/css" href="css/base.css?r=<%= Math.random() %>" />
<script type="text/javascript" src="js/base.js?r=<%= Math.random() %>"></script>
<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {

  });</script>
</head>
<body>
	<div>
		<div class="header">
			<div class="title">bol Mancala Test</div>
		</div>
		<div class="content">
		  <div class="table">
		    <div class="side">
         <div class="pit big enemy" id="pit-13">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
       </div>
       <div class="center">
         <div class="lane">
          <div class="pit small enemy" id="pit-12" onclick="movePit(12)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
	        <div class="pit small enemy" id="pit-11" onclick="movePit(11)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
	        <div class="pit small enemy" id="pit-10" onclick="movePit(10)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
	        <div class="pit small enemy" id="pit-9"  onclick="movePit(9)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
	        <div class="pit small enemy" id="pit-8"  onclick="movePit(8)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
	        <div class="pit small enemy" id="pit-7"  onclick="movePit(7)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
         </div>
         <div class="lane">
           <div class="pit small player" id="pit-0" onclick="movePit(0)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
           <div class="pit small player" id="pit-1" onclick="movePit(1)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
           <div class="pit small player" id="pit-2" onclick="movePit(2)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
           <div class="pit small player" id="pit-3" onclick="movePit(3)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
           <div class="pit small player" id="pit-4" onclick="movePit(4)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
	         <div class="pit small player" id="pit-5" onclick="movePit(5)">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
        </div>
       </div>
       <div class="side">
         <div class="pit big player" id="pit-6">
            <div class="counter">0</div>
            <div class="pocket"></div>
          </div>
       </div>
     </div>
		</div>
		<div class="footer">
      <div id="status"></div>
		</div>
	</div>

  <div id="dialog-player" class="dialog" style="display: none;">
    <input type="hidden" id="player-id">
     <div class="dialog-header">
      <div class="dialog-title">Player name</div>
    </div>
    <div class="dialog-contet">
      <div class="dialog-form"
        style="display: flex; flex-direction: column; align-items: center;">
        <div style="display: flex; align-items: center; margin-top: 10px;">
            <div style="width: 85px;">Name</div>
          <div>
            <input type="text" id="player-name" placeholder="Name">
          </div>
        </div>
      </div>
    </div>
    <div class="dialog-buttons">
      <div class="save" onclick="okPlayer()">OK</div>
      <div class="cancel" onclick="cancelPlayer()">Cancel</div>
    </div>
  </div>

  <div id="shadow"></div>

  <div id="loading" style="display: none;">
    Loading...
  </div>

  <div id="new-game">
    <div class="play" onclick="createPlayer()">Click to play</div>
  </div>

  <div id="notification" style="display: none;"
    onclick="hideNotification()">
    <div id="notification-title"></div>
    <div id="notification-content"></div>
  </div>
</body>
</html>