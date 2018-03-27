
	
	function drawNetwork(){
	// create an array with nodes
	  var nodes = new vis.DataSet([
	    {id: 1, label: 'Asociaciones'},
	    {id: 2, label: 'Autoridades'},
	    {id: 3, label: 'Clase Política'},
	    {id: 4, label: 'Cliente'},
	    {id: 5, label: 'Colaboradores'},
	    {id: 6, label: 'Competidores'},
	    {id: 7, label: 'Comunidades'},
	    {id: 8, label: 'Cuidadanos'}
	  ]);
	
	  // create an array with edges
	  var edges = new vis.DataSet([
	    {from: 1, to: 8, arrows:'to'},
	    {from: 1, to: 3, arrows:'to'},
	    {from: 1, to: 2, arrows:'to, from'},
	    {from: 2, to: 4, arrows:'to, middle'},
	    {from: 2, to: 5, arrows:'to, middle, from'},
	    {from: 5, to: 6, arrows:{to:{scaleFactor:3}}},
	    {from: 6, to: 5, arrows:{to:{scaleFactor:1}}},
	    {from: 6, to: 7, arrows:{middle:{scaleFactor:0.5},from:true}}
	  ]);
	
	  // create a network
	  var container = document.getElementById('mynetwork');
	  var data = {
	    nodes: nodes,
	    edges: edges
	  };
	  var options = {};
	  var network = new vis.Network(container, data, options);
	  
	}
