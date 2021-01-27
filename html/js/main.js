
      setTimeout(() => document.querySelector('title').innerText = 'SimpleCalculator', 1000);
      setTimeout(() => document.querySelector('#app .title').innerText = 'Simple calculator', 500);


      const reset = () => {
        document.getElementById('nr1').value = 0;
        document.getElementById('nr2').value = 0;
      }

      const updateResult = value => {
        document.querySelector('[data-qa-test="result"]').innerHTML = value;
      };

      const calculate = () => {
        // get web elements
        const nr1Element = document.getElementById('nr1');
        const nr2Element = document.getElementById('nr2');
        const operationElement = document.querySelector('.operation-selector');

        const nr1 = nr1Element.value ? parseInt(nr1Element.value) : '';
        const nr2 = nr2Element.value ? parseInt(nr2Element.value) : '';
        const operationId = parseInt(operationElement.value);

        const calculateUrl = '/api/calculate';
        const urlParams = `firstNumber=${nr1}&secondNumber=${nr2}&operation=${operationId}`;
        const requestUrl = `${calculateUrl}?${urlParams}`;


        fetch(requestUrl)
          .then(response => response.json())
          .then(json => updateResult(json.result))
          .catch(err => console.error(err));
      };