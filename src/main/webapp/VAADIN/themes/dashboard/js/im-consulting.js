
	
	function drawNetwork(){
	// create an array with nodes
	  var nodes = new vis.DataSet([
	    {id: 1, label: 'Priority Customer'},
	    {id: 2, label: 'Secondary Customer'},
	    {id: 3, label: 'Dist. Customer'},
	    {id: 4, label: 'Average Customer'},
	    {id: 5, label: 'Prospect Customer'},
	    {id: 6, label: 'KAM'},
	    {id: 7, label: 'Managers'},
	    {id: 8, label: 'Back Office'},
	    {id: 9, label: 'Operatives'},
	    {id: 10, label: 'Unions'},
	    {id: 11, label: 'TV'},
	    {id: 12, label: 'Newspapers'},
	    {id: 13, label: 'Radios'},
	    {id: 14, label: 'RRSS'},
	    {id: 15, label: 'Representatives'},
	    {id: 16, label: 'Senators'},
	    {id: 17, label: 'Government Regulation Agencies'},
	    {id: 18, label: 'Ministers'},
	    {id: 19, label: 'Councilors'},
	    {id: 20, label: 'Mayors'},
	    {id: 21, label: 'NGO’s'},
	    {id: 22, label: 'Competitors'},
	    {id: 23, label: 'Industry Associations'},
	    {id: 24, label: 'Business Associations'},
	    {id: 25, label: 'Trade Unions'},
	    {id: 26, label: 'Community 2'},
	    {id: 27, label: 'Councilors'},
	    {id: 28, label: 'Community 1'},
	    {id: 29, label: 'Community 2'}
	  ]);
	
	  // create an array with edges
	  var edges = new vis.DataSet([
	    {from: 1, to: 2, arrows:'to, from'},
	    {from: 1, to: 3, arrows:'to, from'},
	    {from: 1, to: 4, arrows:'to, from'},
	    {from: 1, to: 6, arrows:'to, from'},
	    {from: 1, to: 7, arrows:'to, from'},
	    {from: 6, to: 4, arrows:'to, from'},
	    {from: 6, to: 2, arrows:'to, from'},
	    {from: 6, to: 5, arrows:'to, from'},
	    {from: 6, to: 3, arrows:'to, from'},
	    {from: 7, to: 4, arrows:'to, from'},
	    {from: 7, to: 8, arrows:'to, from'},
	    {from: 7, to: 9, arrows:'to, from'},
	    {from: 7, to: 10, arrows:'to, from'},	   
	    {from: 8, to: 9, arrows:'to, from'},
	    {from: 10, to: 23, arrows:'to, from'},
	    {from: 10, to: 25, arrows:'to, from'},
	    {from: 11, to: 13, arrows:'to, from'},
	    {from: 11, to: 15, arrows:'to, from'},
	    {from: 11, to: 16, arrows:'to, from'},
	    {from: 12, to: 11, arrows:'to, from'},
	    {from: 12, to: 13, arrows:'to, from'},
	    {from: 12, to: 16, arrows:'to, from'},
	    {from: 12, to: 14, arrows:'to, from'},
	    {from: 13, to: 15, arrows:'to, from'},
	    {from: 13, to: 16, arrows:'to, from'},
	    {from: 14, to: 16, arrows:'to, from'},
	    {from: 14, to: 13, arrows:'to, from'},
	    {from: 14, to: 20, arrows:'to, from'},
	    {from: 17, to: 18, arrows:'to, from'},
	    {from: 17, to: 20, arrows:'to, from'},
	    {from: 17, to: 21, arrows:'to, from'},
	    {from: 20, to: 27, arrows:'to, from'},
	    {from: 20, to: 21, arrows:'to, from'},
	    {from: 20, to: 26, arrows:'to, from'},
	    {from: 20, to: 28, arrows:'to, from'},
	    {from: 20, to: 29, arrows:'to, from'},
	    {from: 23, to: 22, arrows:'to, from'},
	    {from: 23, to: 24, arrows:'to, from'},
	    {from: 23, to: 25, arrows:'to, from'}}
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
