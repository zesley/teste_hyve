var player = null;
var stones = ['circle', 'square', 'triangle','star','pentagon','hexagon','heart'];

function getFormattedDate() {
	var date = new Date();
	var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	var month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
	var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	var second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	return day + "/" + month + "/" + date.getFullYear() + " " + hour + ":" + minute + ":" + second;
}

function movePit(pit) {
	if ($('#pit-' + pit).hasClass('player')) {
		var element = $('#pit-' + pit + ' .pocket').html();
		element += '<div class="stone"><div class="' + stones[Math.floor(Math.random()*stones.length)] + '"></div></div>';
		$('#pit-' + pit + ' .pocket').html(element);
		var matches = $('#pit-' + pit + ' .pocket .stone');
		$('#pit-' + pit).prop('title', 'Stone(s): ' + matches.length);
		$('#pit-' + pit + ' .counter').html(matches.length);
		$('#status').html("Updated at " + getFormattedDate());
	}
}

function loadPoints() {
	$('#shadow').show();
	$('#loading').show();
	$.ajax({
		url : 'rest/points',
		type : 'GET',
		cache : false,
		async : true,
		timeout : 10000,
		dataType : 'json',
		success : function(data, textStatus, jqXHR) {
			var pointTable = "";
			if (data.length > 0) {
				points = data;
				$.each(data, function(i, l) {
					var onMap = false;
					for (var i = 0; i < pointsMap.length; i++) {
						if (pointsMap[i].get("id") === l.id)
							onMap = true
					}
					pointTable += '<tr class="line ' + (onMap ? 'check' : 'uncheck') +'" id="point-' + l.id + '" onclick="togglePoint(' + l.id + ')">';
					pointTable += '<td>' + l.name + '</td>';
					pointTable += '<td>' + l.latitude.toFixed(4) + '</td>';
					pointTable += '<td>' + l.longitude.toFixed(4) + '</td>';
					pointTable += '<td style="padding: 6px; background-color: ' + l.color + '"><i class="icon fas ' + l.category + '"></i></td>';
					pointTable += '<td>' + l.description.substring(0,100) + '</td>';
					pointTable += '<td class="button-like" onclick="editPoint(' + l.id + ')">Edit</td>';
					pointTable += '<td class="button-like" onclick="deletePoint(' + l.id + ')">Delete</td>';
					pointTable += "</tr>";
				});
			} else {
				pointTable = '<tr><td colspan="7">Nothing to show</td></tr>';
			}
			$('#points-table').html(pointTable);
			$('#status').html("Updated at " + getFormattedDate());
			$('#shadow').hide();
			$('#loading').hide();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showNotification("Fail", "Fail on List Points");
			$('#shadow').hide();
			$('#loading').hide();
		}
	});
}

function showNotification(title, content) {
	$('#notification-title').html(title)
	$('#notification-content').html(content)
	$('#notification').show();
}

function hideNotification() {
	$('#notification').hide();
}

function createPlayer () {
	$('#new-game').hide();
	$('#player-id').val(0);
	$('#player-name').val('')
	$('#shadow').show();
	$('#dialog-player').show();
}

function cancelPlayer() {
	$('#new-game').show();
	$('#dialog-player').hide();
}

function okPlayer() {
	$('#dialog-player').hide();
	$('#new-game').hide();
	$('#shadow').hide();
}
