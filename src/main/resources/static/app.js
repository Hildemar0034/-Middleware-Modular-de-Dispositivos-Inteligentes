const API_BASE = "/api";
const devicesList = document.getElementById("devicesList");
const logOutput = document.getElementById("logOutput");
const refreshBtn = document.getElementById("refreshBtn");
const themeToggle = document.getElementById("themeToggle");
const openModal = document.getElementById("openModal");
const modal = document.getElementById("modal");
const cancelBtn = document.getElementById("cancelBtn");
const deviceForm = document.getElementById("deviceForm");
const logFormato = document.getElementById("logFormato");
const setLogFormato = document.getElementById("setLogFormato");

function appendLog(text){
  if(!logOutput) return;
  if(logOutput.innerText.trim() === "Nenhum log ainda.") logOutput.innerText = "";
  logOutput.innerText = text + "\n" + logOutput.innerText;
}

async function loadLogs(){
  try{
    const res = await fetch(`${API_BASE}/logs`);
    const logs = await res.json();
    if(!logs || logs.length===0){ logOutput.innerText = "Nenhum log ainda."; return; }
    logOutput.innerText = logs.slice(-50).reverse().map(l => `[${l.instante}] ${l.mensagem}`).join("\n");
  }catch(e){}
}

async function loadDevices(){
  devicesList.innerHTML = "<li class='device-item'>Carregando...</li>";
  try{
    const res = await fetch(`${API_BASE}/dispositivos`);
    const devices = await res.json();
    renderDevices(devices);
  }catch(err){
    devicesList.innerHTML = `<li class='device-item'>Erro: ${err.message}</li>`;
  }
}

function renderDevices(devices){
  if(!devices || devices.length===0){
    devicesList.innerHTML = "<li class='device-item'>Nenhum dispositivo cadastrado</li>"; return;
  }
  devicesList.innerHTML = "";
  devices.forEach(d => {
    const li = document.createElement("li");
    li.className = 'device-item';
    li.innerHTML = `
      <div class="info">
        <div class="name">${d.nome} <small style="color:var(--muted)">${d.tipo}</small></div>
        <div class="id" style="font-size:12px;color:var(--muted)">id: ${d.id}</div>
      </div>
      <div class="controls">
        <button onclick="sendAction('${d.id}','ligar')">LIGAR</button>
        <button onclick="sendAction('${d.id}','desligar')">DESLIGAR</button>
        <button onclick="toggleSensor('${d.id}')">SIMULAR SENSOR</button>
      </div>
    `;
    devicesList.appendChild(li);
  });
}

async function sendAction(id, acao){
  try{
    const res = await fetch(`${API_BASE}/dispositivo/${id}/acao`,{
      method:"POST",
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ acao })
    });
    const json = await res.json();
    appendLog(`[${new Date().toLocaleTimeString()}] ${json.mensagem}`);
    await loadDevices();
    await loadLogs();
  }catch(err){
    appendLog(`[ERROR ${new Date().toLocaleTimeString()}] ${err.message}`);
  }
}

async function toggleSensor(id){
  const valor = (Math.random()*40).toFixed(1);
  try{
    const res = await fetch(`${API_BASE}/dispositivo/${id}/acao`,{
      method:"POST",
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ acao:"simular", valor: String(valor) })
    });
    const json = await res.json();
    appendLog(`[SENSOR ${new Date().toLocaleTimeString()}] value=${valor} -> ${json.mensagem}`);
    await loadDevices();
    await loadLogs();
  }catch(err){
    appendLog(`[ERROR ${new Date().toLocaleTimeString()}] ${err.message}`);
  }
}

openModal.addEventListener("click", ()=> { modal.className = 'modal-visible'; });
cancelBtn.addEventListener("click", ()=> { modal.className = 'modal-hidden'; });

deviceForm.addEventListener("submit", async (e)=>{
  e.preventDefault();
  const id = document.getElementById("deviceId").value.trim();
  const nome = document.getElementById("deviceNome").value.trim();
  const tipo = document.getElementById("deviceTipo").value.trim();
  try{
    const res = await fetch(`${API_BASE}/dispositivos`, {
      method:"POST",
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify({ id, nome, tipo })
    });
    const json = await res.json();
    appendLog(`[${new Date().toLocaleTimeString()}] ${json.mensagem}`);
    modal.className = 'modal-hidden';
    deviceForm.reset();
    await loadDevices();
    await loadLogs();
  }catch(err){
    appendLog(`[ERROR ${new Date().toLocaleTimeString()}] ${err.message}`);
  }
});

refreshBtn.addEventListener("click", async ()=>{ await loadDevices(); await loadLogs(); });
themeToggle.addEventListener("click", async ()=>{
  document.body.classList.toggle("dark");
  const tema = document.body.classList.contains("dark") ? "escuro" : "claro";
  try{
    await fetch(`${API_BASE}/config/tema`, { method:"POST", headers:{'Content-Type':'application/json'}, body: JSON.stringify({ tema }) });
  }catch(e){}
});

document.getElementById("setLogFormato").addEventListener("click", async ()=>{
  const formato = document.getElementById("logFormato").value;
  try{
    const res = await fetch(`${API_BASE}/log/formato`, { method:"POST", headers:{'Content-Type':'application/json'}, body: JSON.stringify({ formato }) });
    const json = await res.json();
    appendLog(`[${new Date().toLocaleTimeString()}] Formato de log alterado para ${json.formato}`);
    await loadLogs();
  }catch(e){ appendLog(`[ERROR] ${e.message}`); }
});

loadDevices();
loadLogs();
