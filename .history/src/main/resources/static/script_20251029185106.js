const BASE_URL = "http://localhost:8080";
const ENDPOINT = `/projeto`;

let projetos = [];

const $ = (s) => document.querySelector(s);

function toast(msg, ok = true) {
   const t = $("#toast");
   t.className = "toast " + (ok ? "ok" : "err");
   t.textContent = msg;
   t.style.display = "block";
   setTimeout(() => (t.style.display = "none"), 2500);
}

function clearErrors() {
   $("#err-descricao").style.display = "none";
   $("#err-dataFim").style.display = "none";
}

function validarForm() {
   clearErrors();
   const desc = $("#descricao").value.trim();
   const di = $("#dataInicio").value;
   const df = $("#dataFim").value;
   let ok = true;
   if (!desc) {
      $("#err-descricao").textContent = "Informe a descrição";
      $("#err-descricao").style.display = "block";
      ok = false;
   }
   if (di && df) {
      if (new Date(df) < new Date(di)) {
         $("#err-dataFim").textContent = "Data fim não pode ser anterior ao início";
         $("#err-dataFim").style.display = "block";
         ok = false;
      }
   }
   return ok;
}

function escapeHtml(s) {
   return String(s).replace(/[&<>"']/g, (m) => ({
      "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#039;"
   }[m]));
}

function renderTabela(lista) {
   const tbody = $("#tbody");
   if (!lista.length) {
      tbody.innerHTML = `<tr><td colspan="4" class="muted">Nenhum projeto encontrado.</td></tr>`;
      $("#contagem").textContent = 0;
      return;
   }
   tbody.innerHTML = lista.map(p => {
      const equipe = (p.funcionarios || []).map(f => f.nome).join(", ");
      return `<tr>
      <td><strong>${escapeHtml(p.descricao)}</strong></td>
      <td>${escapeHtml(p.dataInicio || "—")}</td>
      <td>${escapeHtml(p.dataFim || "—")}</td>
      <td>${equipe || "—"}</td>
    </tr>`;
   }).join("");
   $("#contagem").textContent = lista.length;
}

async function apiListar() {
   try {
      const r = await fetch(ENDPOINT);
      if (!r.ok) throw new Error("Falha ao listar");
      projetos = await r.json();
      applyFilters();
   } catch {
      toast("Erro ao carregar projetos", false);
   }
}

async function apiCriar(payload) {
   const r = await fetch(ENDPOINT, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
   });
   if (!r.ok) throw new Error("Falha ao criar");
   return r.json();
}

function applyFilters() {
   const q = $("#busca").value.trim().toLowerCase();
   const ord = $("#ordenar").value;
   let list = projetos.filter(p =>
      [p.descricao, (p.funcionarios || []).map(f => f.nome).join(", ")]
         .some(v => String(v || "").toLowerCase().includes(q))
   );
   if (ord === "descricao") list.sort((a, b) => a.descricao.localeCompare(b.descricao));
   else list.sort((a, b) => new Date(b.dataInicio || 0) - new Date(a.dataInicio || 0));
   renderTabela(list);
}

function toggleForm() {
   const card = $("#formCard");
   const btn = $("#toggleForm");
   const vis = card.style.display !== "none";
   card.style.display = vis ? "none" : "block";
   btn.textContent = vis ? "Abrir formulário" : "Fechar formulário";
}

document.addEventListener("DOMContentLoaded", () => {
   apiListar();

   $("#formProjeto").addEventListener("submit", async e => {
      e.preventDefault();
      if (!validarForm()) return;
      const payload = {
         descricao: $("#descricao").value.trim(),
         dataInicio: $("#dataInicio").value || null,
         dataFinal: $("#dataFim").value || null,
      };
      try {
         const novo = await apiCriar(payload);
         projetos.unshift(novo);
         applyFilters();
         $("#formProjeto").reset();
         toast("Projeto cadastrado com sucesso!", true);
      } catch {
         toast("Erro ao salvar projeto", false);
      }
   });

   $("#btnLimpar").addEventListener("click", () => {
      $("#formProjeto").reset(); clearErrors();
   });
   $("#busca").addEventListener("input", applyFilters);
   $("#ordenar").addEventListener("change", applyFilters);
   $("#toggleForm").addEventListener("click", toggleForm);
});