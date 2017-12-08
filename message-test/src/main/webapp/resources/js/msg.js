var config = {
		address : 'http://127.0.0.1:6070',
		clientId : '196846682',
		sercret: '4e07a39dfc7edb7w3d66f2fe4s3911e2',
		timestamp : function() {
			return new Date().getTime();
		},
		sign: function(timestamp) {
			return $.md5(config.clientId + timestamp + config.sercret);
		},
		params : function() {
			var params = {};
			params['clientId'] = config.clientId;
			params['time'] = config.timestamp();
			params['sign'] = config.sign(params.time);
			return params;
		}
};

var api = {
};

api.sysInfo = {
		saveOrUpdate : function(sysNo, name, callback) {
			var _data = config.params();
			_data['sysNo'] = sysNo;
			_data['name'] = name;
			jQuery.ajax({
				url : config.address + '/sysInfo/saveOrUpdate',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		}
};

api.userInfo = {
		saveOrUpdate : function(sysNo, userId, phone, email, callback) {
			var _data = config.params();
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			_data['phone'] = phone;
			_data['email'] = email;
			jQuery.ajax({
				url : config.address + '/userInfo/saveOrUpdate',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		}
};

api.msgGroup = {
		saveOrUpdate : function(id, sysNo, name, type, callback) {
			var _data = config.params();
			_data['id'] = id;
			_data['sysNo'] = sysNo;
			_data['name'] = name;
			_data['type'] = type;
			jQuery.ajax({
				url : config.address + '/msgGroup/saveOrUpdate',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		}
};

api.msgInfo = {
		save : function(sysNo, groupId, title, content, sendUserId, receUserIds, callback) {
			var _data = config.params();
			_data['sysNo'] = sysNo;
			_data['groupId'] = groupId;
			_data['title'] = title;
			_data['content'] = content;
			_data['sendUserId'] = sendUserId;
			_data['receUserIds'] = receUserIds;
			jQuery.ajax({
				url : config.address + '/msgInfo/save',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		getCountUnread : function(sysNo, userId, callback) {
			var _data = config.params();
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			jQuery.ajax({
				url : config.address + '/msgInfo/getCountUnread',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		pageQueryUnread : function(page, size, sysNo, userId, groupId, callback) {
			var _data = config.params();
			_data['page'] = page;
			_data['size'] = size;
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			_data['groupId'] = groupId;
			jQuery.ajax({
				url : config.address + '/msgInfo/pageQueryUnread',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		pageQuery : function(page, size, sysNo, userId, groupId, isRead, callback) {
			var _data = config.params();
			_data['page'] = page;
			_data['size'] = size;
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			_data['isRead'] = isRead;
			_data['groupId'] = groupId;
			jQuery.ajax({
				url : config.address + '/msgInfo/pageQuery',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		findGroupUnread : function(sysNo, userId, callback) {
			var _data = config.params();
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			jQuery.ajax({
				url : config.address + '/msgInfo/findGroupUnread',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		getDtl : function(id, sysNo, userId, callback) {
			var _data = config.params();
			_data['id'] = id;
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			jQuery.ajax({
				url : config.address + '/msgInfo/getDtl',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		deleteRece : function(id, sysNo, userId, callback) {
			var _data = config.params();
			_data['id'] = id;
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			jQuery.ajax({
				url : config.address + '/msgInfo/deleteRece',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		},
		updateIsRead : function(id, sysNo, userId, isRead, callback) {
			var _data = config.params();
			_data['id'] = id;
			_data['sysNo'] = sysNo;
			_data['userId'] = userId;
			_data['isRead'] = isRead;
			jQuery.ajax({
				url : config.address + '/msgInfo/updateIsRead',
				data : _data,
				type : 'post',
				dataType : 'json',
				error : function(json){
					alert('异常');
				},
				success : function(json) {
					callback(json);
				}
			});
		}
};
