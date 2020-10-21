let addIngredient = function () {
	let listName = 'ingredients'; 
	let fieldsNames = ['name', 'amount']; 
	let rowIndex = document.querySelectorAll('.item').length;

	let row = document.createElement('div');
	row.classList.add('row', 'item');

	fieldsNames.forEach((fieldName) => {
		let col = document.createElement('div');
		col.classList.add('col', 'form-group');

		let input = document.createElement('input');
		input.type = 'text';
		input.classList.add('form-control');
		input.id = listName + rowIndex + '.' + fieldName;
		input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);

		col.appendChild(input);
		row.appendChild(col);
	});
		
	document.getElementById('ingredientList').appendChild(row);
};
		
let addStep = function () {
	let listName = 'steps'; 
	let fieldsNames = ['stepNumber', 'description']; 
	let rowIndex = document.querySelectorAll('.item').length; 

	let row = document.createElement('div');
    row.classList.add('row', 'item');

    fieldsNames.forEach((fieldName) => {
    	let col = document.createElement('div');
    	col.classList.add('col', 'form-group');

    	let input = document.createElement('input');
    	input.type = 'text';
    	input.classList.add('form-control');
    	input.id = listName + rowIndex + '.' + fieldName;
    	input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);

    	col.appendChild(input);
    	row.appendChild(col);
    });
		
    document.getElementById('stepList').appendChild(row);
};