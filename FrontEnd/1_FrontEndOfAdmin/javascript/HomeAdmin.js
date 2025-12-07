
    // Sample data and UI logic
    const sampleUsers = [
      {id:1,name:'Nguyễn Văn A',email:'a@example.com',role:'user',joined:'2025-11-20'},
      {id:2,name:'Trần Thị B',email:'b@example.com',role:'admin',joined:'2025-10-15'},
      {id:3,name:'Lê Văn C',email:'c@example.com',role:'user',joined:'2025-12-01'},
      {id:4,name:'Phạm D',email:'d@example.com',role:'user',joined:'2025-09-02'}
    ];

    // elements
    const usersTableBody = document.querySelector('#usersTable tbody');
    const usersTableFullBody = document.querySelector('#usersTableFull tbody');
    const statUsersEl = document.getElementById('statUsers');
    const statOrdersEl = document.getElementById('statOrders');
    const statRevenueEl = document.getElementById('statRevenue');
    const chartCanvas = document.getElementById('chart');

    let users = [...sampleUsers];

    function renderStats(){
      statUsersEl.textContent = users.length;
      const pending = Math.floor(Math.random()*10)+2;
      const total = Math.floor(Math.random()*80)+20;
      statOrdersEl.textContent = `${pending} / ${total}`;
      statRevenueEl.textContent = '$' + ((Math.random()*9000)+1000).toFixed(2);
    }

    function renderUsersTable(){
      usersTableBody.innerHTML='';
      users.slice(0,6).forEach(u=>{
        const tr = document.createElement('tr');
        tr.innerHTML = `<td>${u.name}</td><td>${u.email}</td><td class="small">${u.role}</td><td class="small">${u.joined}</td><td class="actions"><button class="btn ghost" data-id="${u.id}" data-action="edit">Edit</button><button class="btn ghost" data-id="${u.id}" data-action="del">Delete</button></td>`;
        usersTableBody.appendChild(tr);
      })

      // full table
      usersTableFullBody.innerHTML='';
      users.forEach(u=>{
        const tr = document.createElement('tr');
        tr.innerHTML = `<td>${u.id}</td><td>${u.name}</td><td>${u.email}</td><td>${u.role}</td><td><button class="btn ghost" data-id="${u.id}" data-action="edit">Edit</button><button class="btn ghost" data-id="${u.id}" data-action="del">Delete</button></td>`;
        usersTableFullBody.appendChild(tr);
      })
    }

    function drawChart(){
      const ctx = chartCanvas.getContext('2d');
      const w = chartCanvas.width; const h = chartCanvas.height;
      ctx.clearRect(0,0,w,h);
      // simple bar chart for 7 days
      const data = Array.from({length:7},()=>Math.floor(Math.random()*120)+20);
      const max = Math.max(...data);
      const padding = 30;
      const barW = (w - padding*2) / data.length - 8;
      ctx.fillStyle = 'rgba(255,255,255,0.08)';
      for(let i=0;i<data.length;i++){
        const x = padding + i*(barW+8);
        const hBar = (data[i]/max)*(h - padding*2);
        ctx.fillRect(x, h - padding - hBar, barW, hBar);
      }
      // labels
      ctx.fillStyle = 'rgba(255,255,255,0.6)';
      ctx.font = '11px sans-serif';
      const days = ['Mon','Tue','Wed','Thu','Fri','Sat','Sun'];
      for(let i=0;i<days.length;i++){
        const x = padding + i*(barW+8) + barW/2 - 10;
        ctx.fillText(days[i], x, h - 8);
      }
    }

    // modal logic
    const modal = document.getElementById('modal');
    const modalTitle = document.getElementById('modalTitle');
    const inputName = document.getElementById('inputName');
    const inputEmail = document.getElementById('inputEmail');
    const inputRole = document.getElementById('inputRole');
    let editingId = null;

    function openAdd(){ editingId = null; modalTitle.textContent='Add user'; inputName.value=''; inputEmail.value=''; inputRole.value='user'; modal.style.display='flex'; }
    function openEdit(id){ const u = users.find(x=>x.id==id); if(!u) return; editingId=id; modalTitle.textContent='Edit user'; inputName.value=u.name; inputEmail.value=u.email; inputRole.value=u.role; modal.style.display='flex'; }

    document.getElementById('openAdd').addEventListener('click',openAdd);
    document.getElementById('btnAdd').addEventListener('click',openAdd);
    document.getElementById('modalClose').addEventListener('click', ()=> modal.style.display='none');
    document.getElementById('modalSave').addEventListener('click',()=>{
      const name = inputName.value.trim(); const email = inputEmail.value.trim(); const role = inputRole.value;
      if(!name||!email){alert('Please fill name and email');return}
      if(editingId){
        const u = users.find(x=>x.id==editingId); u.name=name; u.email=email; u.role=role;
      } else {
        const newId = users.length?Math.max(...users.map(u=>u.id))+1:1;
        users.unshift({id:newId,name, email, role, joined: new Date().toISOString().slice(0,10)});
      }
      modal.style.display='none'; renderAll();
    });

    // delegate table actions
    document.addEventListener('click', e=>{
      const btn = e.target.closest('button'); if(!btn) return;
      const action = btn.dataset.action; const id = btn.dataset.id && Number(btn.dataset.id);
      if(action=='edit') openEdit(id);
      if(action=='del'){
        if(confirm('Delete this user?')){ users = users.filter(u=>u.id!==id); renderAll(); }
      }
    });

    // nav
    document.querySelectorAll('.nav-item').forEach(el=> el.addEventListener('click', ()=>{
      document.querySelectorAll('.nav-item').forEach(x=>x.classList.remove('active'));
      el.classList.add('active');
      const s = el.dataset.section;
      document.getElementById('dashboardView').style.display = s==='dashboard'?'block':'none';
      document.getElementById('usersView').style.display = s==='users'?'block':'none';
    }));

    // quick actions
    document.getElementById('refreshBtn').addEventListener('click', ()=>{ renderAll(); });
    document.getElementById('exportBtn').addEventListener('click', ()=>{
      const csv = ['id,name,email,role,joined', ...users.map(u=>`${u.id},"${u.name}",${u.email},${u.role},${u.joined}`)].join('\n');
      const blob = new Blob([csv],{type:'text/csv'}); const url = URL.createObjectURL(blob); const a=document.createElement('a'); a.href=url; a.download='users.csv'; a.click(); URL.revokeObjectURL(url);
    });

    // search / filter
    document.getElementById('globalSearch').addEventListener('input', e=>{ const q=e.target.value.toLowerCase(); if(!q){ users=[...sampleUsers]; renderAll(); return;} users = sampleUsers.filter(u=> (u.name+u.email).toLowerCase().includes(q)); renderAll(); });
    document.getElementById('filterInput').addEventListener('input', e=>{ const q=e.target.value.toLowerCase(); const rows = usersTableFullBody.querySelectorAll('tr'); rows.forEach(r=>{ r.style.display = r.textContent.toLowerCase().includes(q)?'table-row':'none' }); });

    // sidebar toggle for small screens
    document.getElementById('toggleSidebar').addEventListener('click', ()=>{
      const s = document.querySelector('.sidebar'); s.style.display = s.style.display==='none'?'block':'none';
    });

    function renderAll(){ renderStats(); renderUsersTable(); drawChart(); }

    // initial render
    renderAll();

    // small nicety: update uptime display
    (function(){ const el = document.getElementById('uptime'); const days = Math.floor(Math.random()*300)+10; el.textContent = days + ' days'; })();

