import os
import joblib
import numpy as np
import json

def init():
   global model
   model_path = os.path.join(os.getenv('AZUREML_MODEL_DIR'), 'lgbm_crops.pkl')
   model = joblib.load(model_path)

def run(raw_data):
   data = np.array(json.loads(raw_data)['data'])
   try:
      result = model.predict(data)
      # You can return any JSON-serializable object.
      return result.tolist()
   except Exception as e:
      error = str(e)
      return error